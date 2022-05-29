package org.example;


import com.example.grpc.client.*;
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
        System.out.println("INIT");
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
        System.out.println("FINISH INIT");
    }

    @Override
    public void addUser(addUserRequest request, StreamObserver<addUserResponse> responseStreamObserver) {
        System.out.println("ADD USER");
        addUserResponse.Builder response = addUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
            return;
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("INSERT INTO users VALUES ('" + request.getFirstName() + "', '" + request.getSecondName() + "', '" + request.getEmail() + "', '" + request.getPassword() + "');");
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
            return;
        }
        responseStreamObserver.onNext(response.setCode(true).build());
        responseStreamObserver.onCompleted();
        System.out.println("FINISH ADD USER");
    }

    @Override
    public void containsUser(containsUserRequest request, StreamObserver<containsUserResponse> responseStreamObserver) {
        System.out.println("CONTAINS USER");
        containsUserResponse.Builder response = containsUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            String st = "SELECT count(*) FROM users WHERE email = '" + request.getEmail() + "';";
            System.out.println(st);
            resultSet = statement.executeQuery("SELECT count(*) FROM users WHERE email='" + request.getEmail() + "';");

        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        try {
            resultSet.next();
            if (resultSet.getInt("count") != 1) {
                responseStreamObserver.onNext(response.setCode(false).build());
            } else {
                responseStreamObserver.onNext(response.setCode(true).build());
                responseStreamObserver.onCompleted();
            }
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        System.out.println("FINISH CONTAINS USER");
    }

    @Override
    public void containsPassword(containsPasswordRequest request, StreamObserver<containsPasswordResponse> responseStreamObserver) {
        System.out.println("CONTAINS PASSWORD");
        containsPasswordResponse.Builder response = containsPasswordResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT count(*) FROM users WHERE email = '" + request.getEmail() + "' AND password = '" + request.getPassword() + "';");
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        try {
            assert resultSet != null;
            resultSet.next();
            if (resultSet.getInt("count") != 1) {
                responseStreamObserver.onNext(response.setCode(false).build());
            } else {
                responseStreamObserver.onNext(response.setCode(true).build());
                responseStreamObserver.onCompleted();
            }
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        System.out.println("FINISH CONTAINS PASSWORD");
    }

    @Override
    public void getUser(getUserRequest request, StreamObserver<getUserResponse> responseStreamObserver) {
//        responseStreamObserver.onNext(GreetingProto.getUserResponse.newBuilder().
//                setFirstName("bobaba").
//                setSecondName("boba").
//                setEmail("abpba").
//                setPassword("aboba").
//                setFirstName("bobaba").
//                build());
//        responseStreamObserver.onCompleted();
        System.out.println("GET USER");
        getUserResponse.Builder response = getUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '" + request.getLogin() + "';");
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        try {
            assert resultSet != null;
            resultSet.next();
            responseStreamObserver.onNext(response
                    .setFirstName(resultSet.getString("firstName"))
                    .setSecondName(resultSet.getString("secondName"))
                    .setEmail(resultSet.getString("email"))
                    .setPassword(resultSet.getString("password"))
                    .build());
            responseStreamObserver.onCompleted();
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        System.out.println("FINISH GET USER");
    }

    @Override
    public void getUserTracks(getUserTracksRequest request, StreamObserver<getUserTracksResponse> responseStreamObserver) {
        System.out.println("GET USER TRACKS");
        getUserTracksResponse.Builder response = getUserTracksResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * from mixed_tracks WHERE author = '" + request.getLogin() + "';");
        } catch (SQLException e) {
            responseStreamObserver.onCompleted();
        }
        List<String> names = new ArrayList<>();
        List<String> author = new ArrayList<>();
        List<String> url = new ArrayList<>();
        List<Integer> id = new ArrayList<>();
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                names.add(resultSet.getString("name"));
                author.add(resultSet.getString("author"));
                url.add(resultSet.getString("url"));
                id.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {

        }
        responseStreamObserver.onNext(response
                .addAllName(names)
                .addAllAuthor(author)
                .addAllUrl(url)
                .addAllId(id)
                .build());
        responseStreamObserver.onCompleted();
        System.out.println("FINISH GET USER TRACKS");
    }

    @Override
    public void addTrackToUser(addTrackToUserRequest request, StreamObserver<addTrackToUserResponse> responseStreamObserver) {
        System.out.println("ADD TRACK TO USER");

        addTrackToUserResponse.Builder response = addTrackToUserResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        assert statement != null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("INSERT INTO mixed_tracks VALUES ('" + request.getName() + "', '" + request.getAuthor() + "', '" + request.getUrl() + "', " + request.getId() + ");");
        } catch (SQLException e) {
            responseStreamObserver.onNext(response.setCode(false).build());
            responseStreamObserver.onCompleted();
        }
        responseStreamObserver.onNext(response.setCode(true).build());
        responseStreamObserver.onCompleted();
        System.out.println("FINISH ADD TRACK TO USER");

    }

    @Override
    public void getDefaultTracks(com.google.protobuf.Empty empty, StreamObserver<getDefaultTracksResponse> responseStreamObserver) {
        System.out.println("GET DEFAULT TRACKS");
        getDefaultTracksResponse.Builder response = getDefaultTracksResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            responseStreamObserver.onNext(null);
            responseStreamObserver.onCompleted();
        }
        ResultSet resultSet = null;
//        try {
//            resultSet = statement.executeQuery("")
//        }
        response.addAllAuthor(new ArrayList<>());
        response.addAllId(new ArrayList<>());
        response.addAllUrl(new ArrayList<>());
        response.addAllName(new ArrayList<>());
        responseStreamObserver.onNext(response.build());
        responseStreamObserver.onCompleted();
        System.out.println("FINISH GET DEFAULT TRACKS");
    }

    public void getAllUserEmails(com.google.protobuf.Empty empty, StreamObserver<getAllUserEmailsResponse> responseStreamObserver) {
        System.out.println("GET ALL USER EMAILS");
        getAllUserEmailsResponse.Builder response = getAllUserEmailsResponse.newBuilder();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("(");
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM users;");
        } catch (SQLException e) {
            responseStreamObserver.onNext(null);
            responseStreamObserver.onCompleted();
        }
        ArrayList<String> emails = new ArrayList<>();
        try{
            while (resultSet.next()){
                emails.add(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        responseStreamObserver.onNext(response.addAllName(emails).build());
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

