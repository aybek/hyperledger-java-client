import org.hyperledger.fabric.sdk.exception.EnrollmentException;
import org.hyperledger.fabric.sdk.exception.RegistrationException;

import java.security.cert.CertificateException;

/**
 * Created by Aybek.Bukabayev on 12/12/16.
 */
public class Main {
    public static void main(String args[]) throws CertificateException, EnrollmentException, RegistrationException {
        Fabric fabric = new Fabric();
        fabric.init("chain1","grpcs://db84cbc578fc4f95a958bcc33cc7ceab-ca.us.blockchain.ibm.com:30001","grpcs://db84cbc578fc4f95a958bcc33cc7ceab-vp0.us.blockchain.ibm.com:30001");
        fabric.enroll("admin","ffc16498e6");
    }
}
