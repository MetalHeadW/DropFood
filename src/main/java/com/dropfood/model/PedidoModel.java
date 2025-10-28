package com.dropfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "HORA")
    private LocalTime hora;

    @Column(name = "DATA")
    private LocalDate data;

    @Column (name="STSPEDIDO")
    private String stsPedido;

    @Column(name = "VLRTOTAL")
    private Double vlrTotal;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "IDEMPRESA")
    private EmpresaModel empresa;

    @Column (name = "TPPAGAMENTO")
    private String tpPagamento;  // Dinheiro, cartão de crédito, debito, etc......

    @Column (name = "STSPAGAMENTO")
    private String stsPagamento; // A receber, conluído.......

    @Column (name = "DTPAGAMENTO")
    private LocalDate dtPagamento;
}