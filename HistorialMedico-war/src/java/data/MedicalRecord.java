
package data;

public class MedicalRecord {
    private String dni;
    private String info;
    private String date;

    public MedicalRecord(String dni, String info, String date) {
        this.dni = dni;
        this.info = info;
        this.date = date;
    }
    
    public MedicalRecord(String dni){
        this.dni = dni;
        this.info = "";
        this.date = "";
    }
    
    public MedicalRecord(){
        this.dni = "";
        this.info = "";
        this.date = "";
    }
    
    public void setDni(String dni) { this.dni = dni; }

    public void setInfo(String info) { this.info = info; }

    public void setDate(String date) { this.date = date; }
      
    public String getDni() { return dni; }

    public String getInfo() { return info; }
    
    public String getDate() { return date; }

    public String printAll(){
        return "Dni: " + dni + " <br> Observaciones: " + info + " <br> Fecha: " + date + " <br> --------------------- <br>";
    }

    public boolean getRecord(String dni){
        return this.dni.equals(dni);
    }
}
