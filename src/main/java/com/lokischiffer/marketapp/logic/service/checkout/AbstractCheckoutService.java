package com.lokischiffer.marketapp.logic.service.checkout;

import com.lokischiffer.marketapp.db.model.ProductDb;
import com.lokischiffer.marketapp.db.repository.ProductRepository;
import com.lokischiffer.marketapp.logic.dto.ProductDto;
import com.lokischiffer.marketapp.logic.exceptions.custom.BadRequestException;
import com.lokischiffer.marketapp.logic.exceptions.custom.ConflictException;
import com.lokischiffer.marketapp.logic.exceptions.custom.ParameterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCheckoutService<T extends ProductDto> {

    @Autowired
    private ProductRepository repository;

    private Checkout checkout;


    protected final ProductDto createInternal(T product) {
//        if (loginInternal(user) == null) {
//            throw new NullPointerException("User not identified");
//        }
        if (!verifyInstance()){
            throw new BadRequestException("The checkout is already created");
        } else if (!repository.existsById(product.getId())) {
            throw new ParameterNotFoundException("Product not found");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new BadRequestException("You're trying to reserved a bigger quantity than"
                    + "there is available");
        } else {
            checkout.addProduct(product);
            repository.findById(product.getId()).get().setReservedQuantity(product.getQuantity());
            ProductDto productDto = createProductDto(repository.findById(product.getId()).get());
            productDto.setQuantity(repository.findById(product.getId()).get().getReservedQuantity());
            return productDto;
        }
    }
    protected final ProductDto addInternal(T product) {
        if (verifyInstance()) {
            dropInstance();
            throw new ParameterNotFoundException("There is no checkout created");
        } else if (!repository.existsById(product.getId())) {
            throw new ParameterNotFoundException("There is no product with that ID");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new BadRequestException("You're trying to reserve a bigger quantity than"
                    + " there is available");
        } else if (checkout.verifyProduct(product)) {
            throw new BadRequestException("That product is already on the checkout");
        } else {
            checkout.addProduct(product);
            repository.findById(product.getId()).get().setReservedQuantity(product.getQuantity());
            ProductDto productDto = createProductDto(repository.findById(product.getId()).get());
            productDto.setQuantity(repository.findById(product.getId()).get().getReservedQuantity());
            return productDto;
        }
    }

    protected final ProductDto updateQuantityInternal(long id, ProductDto product) {
        if (verifyInstance()) {
            dropInstance();
            throw new ParameterNotFoundException("There is no checkout created");
        } else if (!repository.existsById(id)) {
            throw new ParameterNotFoundException("There is no product with that ID");
        } else if (product.getId() != id) {
            throw new ConflictException("ID in the URI doesn't match the product ID");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new BadRequestException("You're trying to reserve a bigger quantity than"
                    + " there is available");
        } else if (!checkout.verifyProduct(product)) {
            throw new ParameterNotFoundException("That product is not on the checkout");
        } else {
            int reservedStock = repository.findById(product.getId()).get().getReservedQuantity()
                    + product.getQuantity();
            repository.findById(product.getId()).get().setReservedQuantity(reservedStock);
            ProductDto productDto = createProductDto(repository.findById(product.getId()).get());
            productDto.setQuantity(repository.findById(product.getId()).get().getReservedQuantity());
            return productDto;
        }
    }

    protected final ProductDto removeInternal(String name) {
        if (verifyInstance()) {
            dropInstance();
            throw new ParameterNotFoundException("There is no checkout created");
        } else if (!repository.existsByName(name)) {
            throw new ParameterNotFoundException("There is no product with that name");
        }
        ProductDto product = createProductDto(repository.findByName(name).get());
        if (!checkout.verifyProduct(product)) {
            throw new ParameterNotFoundException("That product is not on the checkout");
        } else {
            checkout.removeProduct(product);
            if (checkout.verifyList()) {
                dropInstance();
            }
            ProductDto productDto = createProductDto(repository.findById(product.getId()).get());
            productDto.setQuantity(repository.findById(product.getId()).get().getReservedQuantity());
            repository.findById(product.getId()).get().setReservedQuantity(0);
            return productDto;
        }
    }

    private boolean verifyInstance() {
        checkout = Checkout.getInstance();
        return checkout.verifyList();
    }

    private void dropInstance() {
        checkout.deleteInstance();
    }

    private ProductDto createProductDto(ProductDb productDb) {
        return new ProductDto(productDb.getId(), productDb.getName(), productDb.getQuantityInStock());
    }

    private int availableStock(ProductDto productDto) {
        return repository.findById(productDto.getId()).get().getQuantityInStock()
                - repository.findById(productDto.getId()).get().getReservedQuantity();
    }
}
