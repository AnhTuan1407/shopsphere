package com.tuanha.order.mapper;

import com.tuanha.order.dto.request.CartCreationRequest;
import com.tuanha.order.dto.response.CartItemResponse;
import com.tuanha.order.dto.response.CartResponse;
import com.tuanha.order.entity.Cart;
import com.tuanha.order.entity.CartItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartResponse toCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse.CartResponseBuilder cartResponse = CartResponse.builder();

        cartResponse.id( cart.getId() );
        cartResponse.profileId( cart.getProfileId() );
        cartResponse.totalPrice( cart.getTotalPrice() );
        cartResponse.cartItems( cartItemListToCartItemResponseList( cart.getCartItems() ) );

        return cartResponse.build();
    }

    @Override
    public Cart toCart(CartCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Cart.CartBuilder cart = Cart.builder();

        cart.profileId( request.getProfileId() );

        return cart.build();
    }

    protected CartItemResponse cartItemToCartItemResponse(CartItem cartItem) {
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

    protected List<CartItemResponse> cartItemListToCartItemResponseList(List<CartItem> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemResponse> list1 = new ArrayList<CartItemResponse>( list.size() );
        for ( CartItem cartItem : list ) {
            list1.add( cartItemToCartItemResponse( cartItem ) );
        }

        return list1;
    }
}
