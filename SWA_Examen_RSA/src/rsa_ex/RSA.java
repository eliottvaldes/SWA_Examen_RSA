package rsa_ex;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    int tamPrimo;
    BigInteger p, q, n;
    BigInteger phi;
    BigInteger e, d;

    public RSA(int tamPrimo){
        this.tamPrimo = tamPrimo;
    }

    public void generarPrimos(){
        p = new BigInteger(tamPrimo, 10, new Random());
        do q = new BigInteger(tamPrimo, 10, new Random());
            while(q.compareTo(p)==0);
    }

    public void generarClaves(){
        n = p.multiply(q);
        phi = p.subtract(BigInteger.valueOf(1));
        phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));

        do e = new BigInteger(2 * tamPrimo, new Random());
            while((e.compareTo(phi)) != -1 || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));


        d = e.modInverse(phi);
    }

    public static BigInteger[] encriptar(String mensaje, BigInteger e, BigInteger n){

        int i;
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];

        for(i=0; i<bigdigitos.length; i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        BigInteger[] cifrado = new BigInteger[bigdigitos.length];

        for(i=0; i<bigdigitos.length; i++){
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }

        return (cifrado);

    }

    public static String desencriptar(BigInteger[] cifrado, BigInteger d, BigInteger n){

        BigInteger[] descifrado = new BigInteger[cifrado.length];

        for(int i=0; i<descifrado.length; i++){
            descifrado[i] = cifrado[i].modPow(d, n);
        }

        char[] charArray = new char[descifrado.length];

        for(int i=0; i<charArray.length; i++){
            charArray[i] = (char)(descifrado[i].intValue());
        }

        return (new String(charArray));

    }
    
    public static BigInteger obtenerD(BigInteger e, BigInteger n){
        BigInteger d = e.modInverse(n);
        return d;
    }

    //metodos para enviar p, q, n, phi, e, d

    public BigInteger damep(){
        return p;
    }

    public BigInteger dameq(){
        return q;
    }

    public BigInteger damephi(){
        return phi;
    }

    public BigInteger damen(){
        return n;
    }

    public BigInteger damee(){
        return e;
    }

    public BigInteger damed(){
        return d;
    }

}