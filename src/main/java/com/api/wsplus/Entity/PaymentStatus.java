package com.api.wsplus.Entity;

public enum PaymentStatus {
    PENDING, // Pagamento ainda não concluído
    PAID,    // Pagamento realizado com sucesso
    FAILED,  // Pagamento falhou
    REFUNDED // Pagamento reembolsado
}
