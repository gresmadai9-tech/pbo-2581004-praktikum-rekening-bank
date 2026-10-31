import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Rekening {
    // 1. Field semuanya private
    private String nomor;
    private String pemilik;
    private int saldo;

    // 2. Constructor dengan nama parameter yang sama dengan nama field
    public Rekening(String nomor, String pemilik, int saldo) {
        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldo;
    }

    // 3. Saldo read-only: ada getSaldo(), tetapi tidak ada setSaldo
    public int getSaldo() {
        return this.saldo;
    }

    // 4. Method setor
    public boolean setor(int jumlah) {
        if (jumlah <= 0) {
            return false;
        }
        this.saldo += jumlah;
        return true;
    }

    // 4. Method tarik
    public boolean tarik(int jumlah) {
        if (jumlah <= 0 || jumlah > this.saldo) {
            return false;
        }
        this.saldo -= jumlah;
        return true;
    }

    // 5. Method private formatRupiah (Mengubah angka ke format mata uang)
    private String formatRupiah(int uang) {
        DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("Rp");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);

        // Menghilangkan ,00 di belakang jika menggunakan format default
        String hasil = df.format(uang);
        if (hasil.endsWith(",00")) {
            hasil = hasil.substring(0, hasil.length() - 3);
        }
        return hasil;
    }

    // 5. Method private cetakInfo()
    private void cetakInfo() {
        System.out.printf("%s | %s \t | \t %s\n", this.nomor, this.pemilik, formatRupiah(this.saldo));
    }

    // Karena cetakInfo() private, kita buat method public bantuan agar bisa dipanggil dari Main
    public void tampilkanInfo() {
        cetakInfo();
    }
}
