package org.example;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingProto;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    String url = "jdbc:postgresql://abul.db.elephantsql.com:5432/nspkcjrg", user = "nspkcjrg", password = "HvwCWSw9piCjZdNVL7MpHoBMSgCLDRFY";
    Connection connection = null;
    Statement statement = null;

    GreetingServiceImpl() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage() + "zxc123");
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!\n");
        } catch (SQLException e) {
            System.out.println("Connection error\n" + e.getMessage());
        }
    }

    @Override
    public void addUser(GreetingProto.addUserRequest request, StreamObserver<GreetingProto.addUserResponse> responseStreamObserver) {
        GreetingProto.addUserResponse.Builder addUserResponse = GreetingProto.addUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(addUserResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
            return;
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("INSERT INTO users VALUES (" + request.getFirstName() + ", " + request.getSecondName() + ", " + request.getEmail() + ", " + request.getPassword() + ");");
        } catch (SQLException e) {
            responseStreamObserver.onNext(addUserResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
            return;
        }
        responseStreamObserver.onNext(addUserResponse.setCode(true).build());
        responseStreamObserver.onCompleted();
    }

    @Override
    public void containsUser(GreetingProto.containsUserRequest request, StreamObserver<GreetingProto.containsUserResponse> responseStreamObserver) {
        GreetingProto.containsUserResponse.Builder containsUserResponse = GreetingProto.containsUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(containsUserResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT count(*) FROM users WHERE email = " + request.getEmail() + ";");
        } catch (SQLException e) {
            responseStreamObserver.onNext(containsUserResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        try {
            assert resultSet != null;
            resultSet.next();
            if (resultSet.getInt("count") != 1) {
                responseStreamObserver.onNext(containsUserResponse.setCode(false).build());
            } else {
                responseStreamObserver.onNext(containsUserResponse.setCode(true).build());
                responseStreamObserver.onCompleted();
            }
        } catch (SQLException e) {
            responseStreamObserver.onNext(containsUserResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
    }

    @Override
    public void containsPassword(GreetingProto.containsPasswordRequest request, StreamObserver<GreetingProto.containsPasswordResponse> responseStreamObserver) {
        GreetingProto.containsPasswordResponse.Builder containsPasswordResponse = GreetingProto.containsPasswordResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(containsPasswordResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT count(*) FROM users WHERE email = " + request.getEmail() + " AND password = " + request.getPassword() + ";");
        } catch (SQLException e) {
            responseStreamObserver.onNext(containsPasswordResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        try {
            assert resultSet != null;
            resultSet.next();
            if (resultSet.getInt("count") != 1) {
                responseStreamObserver.onNext(containsPasswordResponse.setCode(false).build());
            } else {
                responseStreamObserver.onNext(containsPasswordResponse.setCode(true).build());
                responseStreamObserver.onCompleted();
            }
        } catch (SQLException e) {
            responseStreamObserver.onNext(containsPasswordResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
    }

    @Override
    public void getUser(GreetingProto.getUserRequest request, StreamObserver<GreetingProto.getUserResponse> responseStreamObserver) {
        GreetingProto.getUserResponse.Builder getUserResponse = GreetingProto.getUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM users WHERE email = " + request.getLogin() + ";");
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        try {
            assert resultSet != null;
            resultSet.next();
            responseStreamObserver.onNext(getUserResponse
                    .setFirstName(resultSet.getString("firstName"))
                    .setSecondName(resultSet.getString("secondName"))
                    .setEmail(resultSet.getString("email"))
                    .setPassword(resultSet.getString("password"))
                    .build());
            responseStreamObserver.onCompleted();
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
    }

    @Override
    public void getUserTracks(GreetingProto.getUserTracksRequest request, StreamObserver<GreetingProto.getUserTracksResponse> responseStreamObserver) {
        GreetingProto.getUserTracksResponse.Builder getUserTracksResponse = GreetingProto.getUserTracksResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * from mixed_tracks WHERE author = " + request.getLogin() + ";");
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        try {
            assert resultSet != null;
            List<String> names = new ArrayList<>();
            List<String> author = new ArrayList<>();
            List<String> url = new ArrayList<>();
            List<Integer> id = new ArrayList<>();
            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
                author.add(resultSet.getString("author"));
                url.add(resultSet.getString("url"));
                id.add(resultSet.getInt("id"));
            }
            responseStreamObserver.onNext(getUserTracksResponse
                    .addAllName(names)
                    .addAllAuthor(author)
                    .addAllUrl(url)
                    .addAllId(id)
                    .build());
            responseStreamObserver.onCompleted();
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
    }

    @Override
    public void addTrackToUser(GreetingProto.addTrackToUserRequest request, StreamObserver<GreetingProto.addTrackToUserResponse> responseStreamObserver) {
        GreetingProto.addTrackToUserResponse.Builder addTrackToUserResponse = GreetingProto.addTrackToUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(addTrackToUserResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("INSERT INTO mixed_tracks VALUES (" + request.getName() + ", " + request.getAuthor() + ", " + request.getUrl() + ", " + request.getId() + ");");
        } catch (SQLException e) {
            responseStreamObserver.onNext(addTrackToUserResponse.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        responseStreamObserver.onNext(addTrackToUserResponse.setCode(true).build());
        responseStreamObserver.onCompleted();
    }
//    @Override
//    public void userMixedTracks(GreetingProto.requestUserMixedTracks request, StreamObserver<GreetingServiceOuterClass.responseUserMixedTracks> responseObserver) {
//        System.out.println(request);
//        GreetingServiceOuterClass.responseUserMixedTracks.Builder responseUserMixedTracks = GreetingServiceOuterClass.responseUserMixedTracks.newBuilder();
//        int user_id = request.getId();
//        try {
//            statement = connection.createStatement();
//        } catch (SQLException e) {
//            System.out.println("Connection error\n" + e.getMessage());
//        }
//        assert statement != null;
//        ResultSet result = null;
//        ArrayList<String> arr = new ArrayList<>();
//        try {
//            result = statement.executeQuery("SELECT * FROM mixed_tracks where user_id=" + user_id + ";");
//            while (result.next()) {
//                arr.add(result.getString("name"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        responseObserver.onNext(responseUserMixedTracks.addAllName(arr).build());
//        responseObserver.onCompleted();
//    }
}
