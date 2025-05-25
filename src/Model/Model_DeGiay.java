package Model;

public class Model_DeGiay {

    private String maDeGiay;
    private String tenDeGiay;
    private int TrangThai;

    public void setMaDeGiay(String maDeGiay) {
        this.maDeGiay = maDeGiay;
    }

    public void setTenDeGiay(String tenDeGiay) {
        this.tenDeGiay = tenDeGiay;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaDeGiay() {
        return maDeGiay;
    }

    public String getTenDeGiay() {
        return tenDeGiay;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public Model_DeGiay(String maDeGiay, String tenDeGiay, int TrangThai) {
        this.maDeGiay = maDeGiay;
        this.tenDeGiay = tenDeGiay;
        this.TrangThai = TrangThai;
    }

    public Model_DeGiay() {
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaDeGiay(), this.getTenDeGiay(), this.getTrangThai() == 1 ? "Đang Hoạt động" : "Không Hoạt Động"
        };
    }
}
