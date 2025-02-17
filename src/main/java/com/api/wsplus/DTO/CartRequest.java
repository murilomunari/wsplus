package com.api.wsplus.DTO;

import java.util.List;

public class CartRequest {

    private CartDTO cartDTO;
    private List<CartItemDTO> cartItemDTOs;


    public CartRequest(CartDTO cartDTO, List<CartItemDTO> cartItemDTOs) {
        this.cartDTO = cartDTO;
        this.cartItemDTOs = cartItemDTOs;
    }

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    public List<CartItemDTO> getCartItemDTOs() {
        return cartItemDTOs;
    }

    public void setCartItemDTOs(List<CartItemDTO> cartItemDTOs) {
        this.cartItemDTOs = cartItemDTOs;
    }
}
