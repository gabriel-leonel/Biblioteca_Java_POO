package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
   private int idReserva;
   private Date dataReserva;
   private String status;

    public Reserva(int idReserva, Date dataReserva, String status) {
        this.idReserva = idReserva;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void registrarReserva(){

    }

    public void cancelarReserva(){

    }

    public void confirmarReserva() {

    }
    @Override

    public String toString() {
        return "Reserva \nIdReserva= "+idReserva+"\nDataReserva= "+dataReserva+"\nStatus= "+status + "\n";
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return idReserva + ";" +
                (dataReserva != null ? sdf.format(dataReserva) : "") + ";" +
                (status != null ? status : "");
    }
}