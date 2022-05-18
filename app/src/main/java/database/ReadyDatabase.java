package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingProto;

import org.checkerframework.checker.units.qual.A;

public class ReadyDatabase implements DataBase {


    @Override
    public boolean add(User user) throws IOException {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingProto.addUserRequest addUserRequest = GreetingProto.addUserRequest.newBuilder()
                .setFirstName(user.getFirstName())
                .setSecondName(user.getSecondName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .build();
        GreetingProto.addUserResponse addUserResponse = stub.addUser(addUserRequest);
        return addUserResponse.getCode();
    }

    @Override
    public boolean containsUser(String login) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingProto.containsUserRequest containsUserRequest = GreetingProto.containsUserRequest.newBuilder()
                .setEmail(login)
                .build();
        GreetingProto.containsUserResponse containsUserResponse = stub.containsUser(containsUserRequest);
        return containsUserResponse.getCode();
    }

    @Override
    public boolean containsPassword(String login, String password) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingProto.containsPasswordRequest containsPasswordRequest = GreetingProto.containsPasswordRequest.newBuilder()
                .setEmail(login)
                .setPassword(password)
                .build();
        GreetingProto.containsPasswordResponse containsPasswordResponse = stub.containsPassword(containsPasswordRequest);
        return containsPasswordResponse.getCode();
    }

    @Override
    public boolean removeUser(User user) {
        return false;
    }

    @Override
    public User getUser(String login) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingProto.getUserRequest getUserRequest = GreetingProto.getUserRequest.newBuilder()
                .setLogin(login)
                .build();
        GreetingProto.getUserResponse getUserResponse = stub.getUser(getUserRequest);
        GreetingProto.getUserTracksRequest getUserTracksRequest = GreetingProto.getUserTracksRequest.newBuilder()
                .setLogin(login)
                .build();
        GreetingProto.getUserTracksResponse getUserTracksResponse = stub.getUserTracks(getUserTracksRequest);
        ArrayList<Track> tracks = new ArrayList<>();
        for (int i = 0; i < getUserTracksResponse.getAuthorCount(); i++) {
            tracks.add(new Track(getUserTracksResponse.getName(i), getUserTracksResponse.getAuthor(i), getUserTracksResponse.getUrl(i), getUserTracksResponse.getId(i)));
        }
        return new User(getUserResponse.getFirstName(), getUserResponse.getSecondName(), getUserResponse.getEmail(), getUserResponse.getPassword(), tracks);
    }

    @Override
    public boolean addTrackToUser(String email, Track track) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingProto.addTrackToUserRequest addTrackToUserRequest = GreetingProto.addTrackToUserRequest.newBuilder()
                .setLogin(email)
                .setName(track.getName())
                .setAuthor(track.getAuthor())
                .setUrl(track.getUrl())
                .setId(track.getId())
                .build();
        GreetingProto.addTrackToUserResponse addTrackToUserResponse = stub.addTrackToUser(addTrackToUserRequest);
        return addTrackToUserResponse.getCode();
    }

    @Override
    public ArrayList<Track> getDefaultTracks() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingProto.getDefaultTracksResponse getDefaultTracksResponse = stub.getDefaultTracks(com.google.protobuf.Empty.getDefaultInstance());
        ArrayList<Track> tracks = new ArrayList<>();
        for (int i = 0; i < getDefaultTracksResponse.getAuthorCount(); i++) {
            tracks.add(new Track(getDefaultTracksResponse.getName(i), getDefaultTracksResponse.getAuthor(i), getDefaultTracksResponse.getUrl(i), getDefaultTracksResponse.getId(i)));
        }
        return tracks;
    }

    @Override
    public ArrayList<String> getAllUserEmails() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingProto.getAllUserEmailsResponse getAllUserEmailsResponse = stub.getAllUserEmails(com.google.protobuf.Empty.getDefaultInstance());
        return (ArrayList<String>) getAllUserEmailsResponse.getNameList();
    }


    @Override
    public boolean deleteTrack(String login, String name) {
        //детали обговорим...
        return false;
    }


}