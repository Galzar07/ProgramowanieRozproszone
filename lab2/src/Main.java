public class Main {
    public static void main(String[] args) {

        M_prostokatow p = new M_prostokatow(2,3,2);
        M_trapezow t = new M_trapezow(2,3,2);
        M_simpsona s = new M_simpsona(2,3,2);

        p.start();
        t.start();
        s.start();

        try {
            p.join();
            t.join();
            s.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Suma = " + (p.suma + t.suma + s.suma));
    }
}

class M_prostokatow extends Thread{
    double suma,a,b,n;

    private static double calka(double x) {
        return x*x;
    }
    public M_prostokatow(double a, double b,double n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    public void run() {
        double h;
        n = 3;
        h = (b - a) / n;
        suma = 0;
        for (int i = 1; i <= n; i++) {
            suma += calka(a + i * h);
        }
        suma *= h;
        System.out.println("WYNIK PROSTOKATOW: "+suma);
    }
}

class M_trapezow extends Thread{
    double suma,a,b,n;

    public  M_trapezow(double a, double b,double n){
        this.a = a;
        this.b = b;
        this.n = n;
    }

    private static double calka(double x) {
        return x*x;
    }

    public void run(){
        double h;
        h = (b - a) / n;
        suma = 0;

        for(int i = 1; i < n ; i++){
            suma = suma + calka(a + i * h);
        }
        suma = h * (suma + ((calka(a) + calka(b))) / 2);
        System.out.println("WYNIK TRAPEZY: " + suma);

    }
}

class M_simpsona extends Thread{
    double suma,a,b,n;

    public  M_simpsona(double a, double b,double n){
        this.a = a;
        this.b = b;
        this.n = n;
    }

    private static double calka(double x) {
        return x*x;
    }

    public void run(){
        double h,ti;
        h = (b - a) / n;
        suma = 0;
        ti = 0;

        for(int i = 1; i < n; i++){
            ti = ti + calka((a + i * h) - h / 2);
            suma = suma + calka(a + i * h);
        }
        ti = ti + calka(b - h / 2);
        suma = (calka(a) + calka(b) + 2 * suma + 4 * ti)*(h / 6);
        System.out.println("WYNIK SIMSON: "+suma);

    }
}
