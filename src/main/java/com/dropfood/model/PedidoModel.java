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
@EqualsAndHashCode(of = "idPedido")
@Table(name = "TBPEDIDO")
public class PedidoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPEDIDO")
    private Integer idPedido;

    @Column(name = "DATAHORA")
    private LocalDateTime dataHora;

    @Column (name="STSPEDIDO", length = 50, nullable = false)
    private String stsPedido;

    @Column(name = "VLRTOTAL", nullable = false)
    private Double vlrTotal;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "IDEMPRESA", nullable = false)
    private EmpresaModel empresa;

    @Column (name = "TPPAGAMENTO", length = 50)
    private String tpPagamento;  // Dinheiro, cartão de crédito, debito, etc......

    @Column (name = "STSPAGAMENTO", length = 50)
    private String stsPagamento; // A receber, conluído.......

    @Column (name = "DTPAGAMENTO")
    private LocalDate dtPagamento;
}