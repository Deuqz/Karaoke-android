package database;

import java.io.IOException;
import java.util.ArrayList;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.example.grpc.client.GreetingServiceGrpc;
import com.example.grpc.client.*;

public class ReadyDatabase implements DataBase {

    String HOST = "192.168.88.60";


    @Override
    public boolean add(User user) throws IOException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, 50051).usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        addUserRequest request = addUserRequest.newBuilder()
                .setFirstName(user.getFirstName())
                .setSecondName(user.getSecondName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .build();
        addUserResponse response = stub.addUser(request);
        channel.shutdown();
        return response.getCode();
    }

    @Override
    public boolean containsUser(String login) {
//        return false;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, 50051).usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        containsUserRequest request = containsUserRequest.newBuilder()
                .setEmail(login)
                .build();
        containsUserResponse response = stub.containsUser(request);
        return response.getCode();
    }

    @Override
    public boolean containsPassword(String login, String password) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, 50051).usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        containsPasswordRequest request = containsPasswordRequest.newBuilder()
                .setEmail(login)
                .setPassword(password)
                .build();
        containsPasswordResponse response = stub.containsPassword(request);
        channel.shutdown();
        return response.getCode();
    }

    @Override
    public boolean removeUser(User user) {
        return false;
    }

    @Override
    public User getUser(String login) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, 50051).usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        getUserRequest request = getUserRequest.newBuilder()
                .setLogin(login)
                .build();
        getUserResponse response = stub.getUser(request);
        getUserTracksRequest request1 = getUserTracksRequest.newBuilder()
                .setLogin(login)
                .build();
        getUserTracksResponse response1 = stub.getUserTracks(request1);
        ArrayList<Track> tracks = new ArrayList<>();
        for (int i = 0; i < response1.getAuthorCount(); i++) {
            tracks.add(new Track(response1.getName(i), response1.getAuthor(i), response1.getUrl(i), response1.getId(i)));
        }
        channel.shutdown();
        return new User(response.getFirstName(), response.getSecondName(), response.getEmail(), response.getPassword(), tracks);
    }

    @Override
    public boolean addTrackToUser(String email, Track track) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, 50051).usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        addTrackToUserRequest request = addTrackToUserRequest.newBuilder()
                .setLogin(email)
                .setName(track.getName())
                .setAuthor(track.getAuthor())
                .setUrl(track.getUrl())
                .setId(track.getId())
                .build();
        addTrackToUserResponse response = stub.addTrackToUser(request);
        channel.shutdown();
        return response.getCode();
    }

    @Override
    public ArrayList<Track> getDefaultTracks() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, 50051).usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        getDefaultTracksResponse response = stub.getDefaultTracks(com.google.protobuf.Empty.getDefaultInstance());
        channel.shutdown();
        ArrayList<Track> tracks = new ArrayList<>();
        for (int i = 0; i < response.getAuthorCount(); i++) {
            tracks.add(new Track(response.getName(i), response.getAuthor(i), response.getUrl(i), response.getId(i)));
        }
        return tracks;
    }

    @Override
    public ArrayList<String> getAllUserEmails() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, 50051).usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        getAllUserEmailsResponse response = stub.getAllUserEmails(com.google.protobuf.Empty.getDefaultInstance());
        channel.shutdown();
        return new ArrayList<>(response.getNameList());
    }


    @Override
    public boolean deleteTrack(String login, String name) {
        //детали обговорим...
        return false;
    }
}
