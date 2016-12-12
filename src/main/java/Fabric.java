import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.exception.EnrollmentException;
import org.hyperledger.fabric.sdk.exception.PeerException;
import org.hyperledger.fabric.sdk.exception.RegistrationException;
import org.hyperledger.fabric.sdk.transaction.DeployTransactionBuilder;
import org.hyperledger.fabric.sdk.transaction.Transaction;

import java.security.cert.CertificateException;

/**
 * Created by Aybek.Bukabayev on 12/12/16.
 */

public class Fabric {
      public Chain chain;
      public String chaincodeId;
      private Peer vp0;
      private DeployTransactionBuilder deployTransactionBuilder = DeployTransactionBuilder.newBuilder();

      public void init(String chainName, String memberSrvcUrl, String rootNodeUrl) throws CertificateException {
            this.chain = new Chain(chainName);
            this.chain.setMemberServicesUrl(memberSrvcUrl,null);
            this.chain.setKeyValStore(new FileKeyValStore("test.properties"));
            this.vp0 = this.chain.addPeer(rootNodeUrl,null);
      }

     public Member enroll(String username, String secret) throws EnrollmentException, RegistrationException {
         Member user = chain.getMember(username);
         if (!user.isEnrolled()){
             user = chain.enroll(username,secret);
         }
         return user;
     }

     public org.hyperledger.protos.Fabric.Response deployChaincode(String path) throws PeerException {

         TransactionRequest request = new TransactionRequest();
         request.setChaincodePath(path);
         request.setChaincodeLanguage(ChaincodeLanguage.GO_LANG);

         Transaction transaction = deployTransactionBuilder.chain(chain).request(request).build();
         org.hyperledger.protos.Fabric.Response response = vp0.sendTransaction(transaction);
        return  response;
     }

    public org.hyperledger.protos.Fabric.Response invoke(){
        return null;
    }

    public org.hyperledger.protos.Fabric.Response query(){
        return null;
    }
}
