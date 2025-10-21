package com.dropfood.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

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
    private int id_Pedido;
    @Column(name = "DATA_HORA", length = 50)
    private LocalDateTime data_Hora;
    @Column (name="STSPEDIDO", length = 50)
    private String sts_Pedido;
    @Column(name = "VLTOTAL")
    private Double vl_Total;
    @Column (name = "IDUSUARIO")
    private Integer id_Usuario;
    @Column(name = "IDEMPRESA")
    private Integer id_Empresa;
    @Column (name = "TPPAGAMENTO")
    private String tp_Pagamento;
    @Column (name = "STSPAGAMENTO")
    private String sts_Pagamento;
    @Column (name = "DTPAGAMENTO")
    private LocalDate dt_Pagamento;


    public int getId_Pedido(){
        return id_Pedido;
    }

    public void setId_Pedido(int id_Pedido){
        this.id_Pedido = id_Pedido;
    }

    public LocalDateTime getData_Hora(){
        return data_Hora;

    }
    public void setData_Hora(LocalDateTime data_Hora){
        this.data_Hora = data_Hora;
    }

    public String getSts_Pedido(){
        return sts_Pedido;
    }
    public void setSts_Pedido(String sts_pedido){
        this.sts_Pedido = sts_pedido;
    }

    public Double getVl_Total(){
        return vl_Total;
    }
    public void setVl_Total(Double vl_Total){
        this.vl_Total = vl_Total;
    }

public Integer getId_Usuario(){
        return id_Usuario;
}
    public void setId_Usuario(Integer id_Usuario){
        this.id_Usuario = id_Usuario;
    }

    public int getId_Empresa(){
        return id_Empresa;
    }
public void setId_Empresa(int id_Empresa){
        this.id_Empresa = id_Empresa;
}
public String getTp_Pagamento(){
        return tp_Pagamento;
}
public void setTp_Pagamento(String tp_Pagamento){
        this.tp_Pagamento = tp_Pagamento;
}
public String getSts_Pagamento(){
        return sts_Pagamento;
}
public void setSts_Pagamento(String sts_Pagamento){
        this.sts_Pagamento = sts_Pagamento;
}
public LocalDate getDt_Pagamento(){
        return dt_Pagamento;
}
public void setDt_Pagamento(LocalDate dt_Pagamento){
        this.dt_Pagamento = dt_Pagamento;
}

}
