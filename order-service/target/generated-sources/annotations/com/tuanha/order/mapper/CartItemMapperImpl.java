package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.CartItemCreationRequest;
import com.tuanha.order.dto.response.CartItemResponse;
import com.tuanha.order.entity.CartItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItem toCartItem(CartItemCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        CartItem.CartItemBuilder cartItem = CartItem.builder();

        cartItem.productId( request.getProductId() );
        cartItem.productVariantId( request.getProductVariantId() );
        cartItem.supplierId( request.getSupplierId() );
        cartItem.quantity( request.getQuantity() );
        cartItem.price( request.getPrice() );

        return cartItem.build();
    }

    @Override
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponse.CartItemResponseBuilder cartItemResponse = CartItemResponse.builder();

        cartItemResponse.id( cartItem.getId() );
        cartItemResponse.productId( cartItem.getProductId() );
        cartItemResponse.productVariantId( cartItem.getProductVariantId() );
        cartItemResponse.supplierId( cartItem.getSupplierId() );
        cartItemResponse.quantity( cartItem.getQuantity() );
        cartItemResponse.price( cartItem.getPrice() );
        cartItemResponse.selected( cartItem.isSelected() );

        return cartItemResponse.build();
    }

    @Override
    public void toUpdateCartItem(CartItem cartItem, CartItemCreationRequest request) {
        if ( request == null ) {
            return;
        }

        cartItem.setProductId( request.getProductId() );
        cartItem.setProductVariantId( request.getProductVariantId() );
        cartItem.setSupplierId( request.getSupplierId() );
        cartItem.setQuantity( request.getQuantity() );
        cartItem.setPrice( request.getPrice() );
    }
}
