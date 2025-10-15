package com.dropfood.dropfood.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBPEDIDO")

public class PedidoModel {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "IDPEDIDO")
private int idPedido;
@Column (name = "")
private Date qtPedido;

