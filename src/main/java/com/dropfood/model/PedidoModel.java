package com.dropfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_Pedido")
@Table(name = "TBPEDIDO")
public class PedidoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPEDIDO")
    private Integer id_Pedido;

    @Column(name = "DATAHORA")
    private LocalDateTime data_Hora;

    @Column (name="STSPEDIDO", length = 50, nullable = false)
    private String sts_Pedido;

    @Column(name = "VLRTOTAL", nullable = false)
    private Double vlr_Total;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "IDEMPRESA", nullable = false)
    private EmpresaModel empresa;

    @Column (name = "TPPAGAMENTO", length = 50)
    private String tp_Pagamento;  // Dinheiro, cartão de crédito, debito, etc......

    @Column (name = "STSPAGAMENTO", length = 50)
    private String sts_Pagamento; // A receber, conluído.......

    @Column (name = "DTPAGAMENTO")
    private LocalDate dt_Pagamento;
}