//package database;
//
//import com.example.grpc.GreetingProto;
//import com.example.grpc.GreetingServiceGrpc;
//
//import java.util.HashSet;
//
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//
//public class SimpleDatabase implements DataBase {
//
//    static HashSet<String> emails = new HashSet<>();
//    static HashSet<String> emailAndPassword = new HashSet<>();
//
//    @Override
//    public boolean add(User user) {
//        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
//        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
//        GreetingProto.addRequest addRequest = GreetingProto.addRequest.newBuilder().setLogin("Aboba").setPassword("Aboba").build();
//        GreetingProto.addResponse addResponse = stub.greeting(addRequest);
//        System.out.println(addResponse.getGreeting());
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + addResponse.getGreeting());
////        return false;
//
//        boolean result = emails.add(user.getEmail());
//        result &= emailAndPassword.add(user.getEmail() + " " + user.getPassword());
//        return result;
//    }
//
//    @Override
//    public boolean containsUser(String email) {
//        return emails.contains(email);
//    }
//
//    @Override
//    public boolean containsPassword(String email, String password) {
//        return emailAndPassword.contains(email + " " + password);
//    }
//
//    @Override
//    public boolean remove(User user) {
//        boolean result = emails.remove(user.getEmail());
//        result &= emailAndPassword.remove(user.getEmail() + " " + user.getPassword());
//        return result;
//    }
//}
