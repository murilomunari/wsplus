package com.api.wsplus.Service;

import com.api.wsplus.DTO.OrderDTO;
import com.api.wsplus.DTO.OrderItemDTO;
import com.api.wsplus.Entity.*;
import com.api.wsplus.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public Order createOrder(Long cartId, Long shippingAddressId, String paymentMethod) {

        // 1. Buscar carrinho pelo ID
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho está vazio!");
        }

        // 2. Buscar cliente e endereço de entrega
        Client client = cart.getClient();
        Address shippingAddress = addressRepository.findById(shippingAddressId)
                .orElseThrow(() -> new RuntimeException("Endereço de entrega não encontrado!"));

        // 3. Criar os itens do pedido a partir do carrinho
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());

        // 4. Criar o pedido e associar os itens
        Order order = new Order(client, orderItems, cart.getTotalAmount(), paymentMethod, shippingAddress);
        orderItems.forEach(item -> item.setOrder(order));  // Associar os itens ao pedido

        // 5. Salvar o pedido (os itens serão salvos devido ao cascateamento)
        orderRepository.save(order);

        // 6. Representação do pagamento como uma String (pode ser feito com algum status real)
        String paymentStatus = "aprovado"; // Exemplo de status de pagamento

        // 7. Limpar o carrinho após finalização do pedido
        cartItemRepository.deleteAll(cart.getItems());
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);

        return order;
    }


    public OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getOrderDate(),
                order.getClient().getId(),
                itemDTOs,
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getShippingAddress().getId(),
                null // Aqui você pode deixar como null ou um valor fictício de pagamento por enquanto
        );
    }
}
