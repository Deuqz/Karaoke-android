package com.example.grpc.client;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.12.0)",
    comments = "Source: GreetingService.proto")
public final class GreetingServiceGrpc {

  private GreetingServiceGrpc() {}

  public static final String SERVICE_NAME = "client.GreetingService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getAddUserMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.example.grpc.client.addUserRequest,
      com.example.grpc.client.addUserResponse> METHOD_ADD_USER = getAddUserMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.client.addUserRequest,
      com.example.grpc.client.addUserResponse> getAddUserMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.example.grpc.client.addUserRequest,
      com.example.grpc.client.addUserResponse> getAddUserMethod() {
    return getAddUserMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.example.grpc.client.addUserRequest,
      com.example.grpc.client.addUserResponse> getAddUserMethodHelper() {
    io.grpc.MethodDescriptor<com.example.grpc.client.addUserRequest, com.example.grpc.client.addUserResponse> getAddUserMethod;
    if ((getAddUserMethod = GreetingServiceGrpc.getAddUserMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getAddUserMethod = GreetingServiceGrpc.getAddUserMethod) == null) {
          GreetingServiceGrpc.getAddUserMethod = getAddUserMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.client.addUserRequest, com.example.grpc.client.addUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "addUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.addUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.addUserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("addUser"))
                  .build();
          }
        }
     }
     return getAddUserMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getContainsUserMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.example.grpc.client.containsUserRequest,
      com.example.grpc.client.containsUserResponse> METHOD_CONTAINS_USER = getContainsUserMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.client.containsUserRequest,
      com.example.grpc.client.containsUserResponse> getContainsUserMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.example.grpc.client.containsUserRequest,
      com.example.grpc.client.containsUserResponse> getContainsUserMethod() {
    return getContainsUserMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.example.grpc.client.containsUserRequest,
      com.example.grpc.client.containsUserResponse> getContainsUserMethodHelper() {
    io.grpc.MethodDescriptor<com.example.grpc.client.containsUserRequest, com.example.grpc.client.containsUserResponse> getContainsUserMethod;
    if ((getContainsUserMethod = GreetingServiceGrpc.getContainsUserMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getContainsUserMethod = GreetingServiceGrpc.getContainsUserMethod) == null) {
          GreetingServiceGrpc.getContainsUserMethod = getContainsUserMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.client.containsUserRequest, com.example.grpc.client.containsUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "containsUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.containsUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.containsUserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("containsUser"))
                  .build();
          }
        }
     }
     return getContainsUserMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getContainsPasswordMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.example.grpc.client.containsPasswordRequest,
      com.example.grpc.client.containsPasswordResponse> METHOD_CONTAINS_PASSWORD = getContainsPasswordMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.client.containsPasswordRequest,
      com.example.grpc.client.containsPasswordResponse> getContainsPasswordMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.example.grpc.client.containsPasswordRequest,
      com.example.grpc.client.containsPasswordResponse> getContainsPasswordMethod() {
    return getContainsPasswordMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.example.grpc.client.containsPasswordRequest,
      com.example.grpc.client.containsPasswordResponse> getContainsPasswordMethodHelper() {
    io.grpc.MethodDescriptor<com.example.grpc.client.containsPasswordRequest, com.example.grpc.client.containsPasswordResponse> getContainsPasswordMethod;
    if ((getContainsPasswordMethod = GreetingServiceGrpc.getContainsPasswordMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getContainsPasswordMethod = GreetingServiceGrpc.getContainsPasswordMethod) == null) {
          GreetingServiceGrpc.getContainsPasswordMethod = getContainsPasswordMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.client.containsPasswordRequest, com.example.grpc.client.containsPasswordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "containsPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.containsPasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.containsPasswordResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("containsPassword"))
                  .build();
          }
        }
     }
     return getContainsPasswordMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetUserMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.example.grpc.client.getUserRequest,
      com.example.grpc.client.getUserResponse> METHOD_GET_USER = getGetUserMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.client.getUserRequest,
      com.example.grpc.client.getUserResponse> getGetUserMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.example.grpc.client.getUserRequest,
      com.example.grpc.client.getUserResponse> getGetUserMethod() {
    return getGetUserMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.example.grpc.client.getUserRequest,
      com.example.grpc.client.getUserResponse> getGetUserMethodHelper() {
    io.grpc.MethodDescriptor<com.example.grpc.client.getUserRequest, com.example.grpc.client.getUserResponse> getGetUserMethod;
    if ((getGetUserMethod = GreetingServiceGrpc.getGetUserMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getGetUserMethod = GreetingServiceGrpc.getGetUserMethod) == null) {
          GreetingServiceGrpc.getGetUserMethod = getGetUserMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.client.getUserRequest, com.example.grpc.client.getUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "getUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.getUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.getUserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("getUser"))
                  .build();
          }
        }
     }
     return getGetUserMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetUserTracksMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.example.grpc.client.getUserTracksRequest,
      com.example.grpc.client.getUserTracksResponse> METHOD_GET_USER_TRACKS = getGetUserTracksMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.client.getUserTracksRequest,
      com.example.grpc.client.getUserTracksResponse> getGetUserTracksMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.example.grpc.client.getUserTracksRequest,
      com.example.grpc.client.getUserTracksResponse> getGetUserTracksMethod() {
    return getGetUserTracksMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.example.grpc.client.getUserTracksRequest,
      com.example.grpc.client.getUserTracksResponse> getGetUserTracksMethodHelper() {
    io.grpc.MethodDescriptor<com.example.grpc.client.getUserTracksRequest, com.example.grpc.client.getUserTracksResponse> getGetUserTracksMethod;
    if ((getGetUserTracksMethod = GreetingServiceGrpc.getGetUserTracksMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getGetUserTracksMethod = GreetingServiceGrpc.getGetUserTracksMethod) == null) {
          GreetingServiceGrpc.getGetUserTracksMethod = getGetUserTracksMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.client.getUserTracksRequest, com.example.grpc.client.getUserTracksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "getUserTracks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.getUserTracksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.getUserTracksResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("getUserTracks"))
                  .build();
          }
        }
     }
     return getGetUserTracksMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getAddTrackToUserMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.example.grpc.client.addTrackToUserRequest,
      com.example.grpc.client.addTrackToUserResponse> METHOD_ADD_TRACK_TO_USER = getAddTrackToUserMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.client.addTrackToUserRequest,
      com.example.grpc.client.addTrackToUserResponse> getAddTrackToUserMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.example.grpc.client.addTrackToUserRequest,
      com.example.grpc.client.addTrackToUserResponse> getAddTrackToUserMethod() {
    return getAddTrackToUserMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.example.grpc.client.addTrackToUserRequest,
      com.example.grpc.client.addTrackToUserResponse> getAddTrackToUserMethodHelper() {
    io.grpc.MethodDescriptor<com.example.grpc.client.addTrackToUserRequest, com.example.grpc.client.addTrackToUserResponse> getAddTrackToUserMethod;
    if ((getAddTrackToUserMethod = GreetingServiceGrpc.getAddTrackToUserMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getAddTrackToUserMethod = GreetingServiceGrpc.getAddTrackToUserMethod) == null) {
          GreetingServiceGrpc.getAddTrackToUserMethod = getAddTrackToUserMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.client.addTrackToUserRequest, com.example.grpc.client.addTrackToUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "addTrackToUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.addTrackToUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.addTrackToUserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("addTrackToUser"))
                  .build();
          }
        }
     }
     return getAddTrackToUserMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDefaultTracksMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getDefaultTracksResponse> METHOD_GET_DEFAULT_TRACKS = getGetDefaultTracksMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getDefaultTracksResponse> getGetDefaultTracksMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getDefaultTracksResponse> getGetDefaultTracksMethod() {
    return getGetDefaultTracksMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getDefaultTracksResponse> getGetDefaultTracksMethodHelper() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.example.grpc.client.getDefaultTracksResponse> getGetDefaultTracksMethod;
    if ((getGetDefaultTracksMethod = GreetingServiceGrpc.getGetDefaultTracksMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getGetDefaultTracksMethod = GreetingServiceGrpc.getGetDefaultTracksMethod) == null) {
          GreetingServiceGrpc.getGetDefaultTracksMethod = getGetDefaultTracksMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.example.grpc.client.getDefaultTracksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "getDefaultTracks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.getDefaultTracksResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("getDefaultTracks"))
                  .build();
          }
        }
     }
     return getGetDefaultTracksMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetAllUserEmailsMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getAllUserEmailsResponse> METHOD_GET_ALL_USER_EMAILS = getGetAllUserEmailsMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getAllUserEmailsResponse> getGetAllUserEmailsMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getAllUserEmailsResponse> getGetAllUserEmailsMethod() {
    return getGetAllUserEmailsMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.client.getAllUserEmailsResponse> getGetAllUserEmailsMethodHelper() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.example.grpc.client.getAllUserEmailsResponse> getGetAllUserEmailsMethod;
    if ((getGetAllUserEmailsMethod = GreetingServiceGrpc.getGetAllUserEmailsMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getGetAllUserEmailsMethod = GreetingServiceGrpc.getGetAllUserEmailsMethod) == null) {
          GreetingServiceGrpc.getGetAllUserEmailsMethod = getGetAllUserEmailsMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.example.grpc.client.getAllUserEmailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "getAllUserEmails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.getAllUserEmailsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("getAllUserEmails"))
                  .build();
          }
        }
     }
     return getGetAllUserEmailsMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getDeleteTrackMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.example.grpc.client.deleteTrackRequest,
      com.example.grpc.client.deleteTrackResponse> METHOD_DELETE_TRACK = getDeleteTrackMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.client.deleteTrackRequest,
      com.example.grpc.client.deleteTrackResponse> getDeleteTrackMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.example.grpc.client.deleteTrackRequest,
      com.example.grpc.client.deleteTrackResponse> getDeleteTrackMethod() {
    return getDeleteTrackMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.example.grpc.client.deleteTrackRequest,
      com.example.grpc.client.deleteTrackResponse> getDeleteTrackMethodHelper() {
    io.grpc.MethodDescriptor<com.example.grpc.client.deleteTrackRequest, com.example.grpc.client.deleteTrackResponse> getDeleteTrackMethod;
    if ((getDeleteTrackMethod = GreetingServiceGrpc.getDeleteTrackMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getDeleteTrackMethod = GreetingServiceGrpc.getDeleteTrackMethod) == null) {
          GreetingServiceGrpc.getDeleteTrackMethod = getDeleteTrackMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.client.deleteTrackRequest, com.example.grpc.client.deleteTrackResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "client.GreetingService", "deleteTrack"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.deleteTrackRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.client.deleteTrackResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("deleteTrack"))
                  .build();
          }
        }
     }
     return getDeleteTrackMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreetingServiceStub newStub(io.grpc.Channel channel) {
    return new GreetingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreetingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreetingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreetingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreetingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GreetingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addUser(com.example.grpc.client.addUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.addUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddUserMethodHelper(), responseObserver);
    }

    /**
     */
    public void containsUser(com.example.grpc.client.containsUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.containsUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContainsUserMethodHelper(), responseObserver);
    }

    /**
     */
    public void containsPassword(com.example.grpc.client.containsPasswordRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.containsPasswordResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getContainsPasswordMethodHelper(), responseObserver);
    }

    /**
     */
    public void getUser(com.example.grpc.client.getUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserMethodHelper(), responseObserver);
    }

    /**
     */
    public void getUserTracks(com.example.grpc.client.getUserTracksRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getUserTracksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserTracksMethodHelper(), responseObserver);
    }

    /**
     */
    public void addTrackToUser(com.example.grpc.client.addTrackToUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.addTrackToUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddTrackToUserMethodHelper(), responseObserver);
    }

    /**
     */
    public void getDefaultTracks(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getDefaultTracksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDefaultTracksMethodHelper(), responseObserver);
    }

    /**
     */
    public void getAllUserEmails(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getAllUserEmailsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllUserEmailsMethodHelper(), responseObserver);
    }

    /**
     */
    public void deleteTrack(com.example.grpc.client.deleteTrackRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.deleteTrackResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteTrackMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddUserMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.client.addUserRequest,
                com.example.grpc.client.addUserResponse>(
                  this, METHODID_ADD_USER)))
          .addMethod(
            getContainsUserMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.client.containsUserRequest,
                com.example.grpc.client.containsUserResponse>(
                  this, METHODID_CONTAINS_USER)))
          .addMethod(
            getContainsPasswordMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.client.containsPasswordRequest,
                com.example.grpc.client.containsPasswordResponse>(
                  this, METHODID_CONTAINS_PASSWORD)))
          .addMethod(
            getGetUserMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.client.getUserRequest,
                com.example.grpc.client.getUserResponse>(
                  this, METHODID_GET_USER)))
          .addMethod(
            getGetUserTracksMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.client.getUserTracksRequest,
                com.example.grpc.client.getUserTracksResponse>(
                  this, METHODID_GET_USER_TRACKS)))
          .addMethod(
            getAddTrackToUserMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.client.addTrackToUserRequest,
                com.example.grpc.client.addTrackToUserResponse>(
                  this, METHODID_ADD_TRACK_TO_USER)))
          .addMethod(
            getGetDefaultTracksMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                com.example.grpc.client.getDefaultTracksResponse>(
                  this, METHODID_GET_DEFAULT_TRACKS)))
          .addMethod(
            getGetAllUserEmailsMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                com.example.grpc.client.getAllUserEmailsResponse>(
                  this, METHODID_GET_ALL_USER_EMAILS)))
          .addMethod(
            getDeleteTrackMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.client.deleteTrackRequest,
                com.example.grpc.client.deleteTrackResponse>(
                  this, METHODID_DELETE_TRACK)))
          .build();
    }
  }

  /**
   */
  public static final class GreetingServiceStub extends io.grpc.stub.AbstractStub<GreetingServiceStub> {
    private GreetingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceStub(channel, callOptions);
    }

    /**
     */
    public void addUser(com.example.grpc.client.addUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.addUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddUserMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void containsUser(com.example.grpc.client.containsUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.containsUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContainsUserMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void containsPassword(com.example.grpc.client.containsPasswordRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.containsPasswordResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContainsPasswordMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUser(com.example.grpc.client.getUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserTracks(com.example.grpc.client.getUserTracksRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getUserTracksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserTracksMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addTrackToUser(com.example.grpc.client.addTrackToUserRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.addTrackToUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddTrackToUserMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDefaultTracks(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getDefaultTracksResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDefaultTracksMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllUserEmails(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.getAllUserEmailsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllUserEmailsMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteTrack(com.example.grpc.client.deleteTrackRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.client.deleteTrackResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteTrackMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreetingServiceBlockingStub extends io.grpc.stub.AbstractStub<GreetingServiceBlockingStub> {
    private GreetingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.client.addUserResponse addUser(com.example.grpc.client.addUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddUserMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.containsUserResponse containsUser(com.example.grpc.client.containsUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getContainsUserMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.containsPasswordResponse containsPassword(com.example.grpc.client.containsPasswordRequest request) {
      return blockingUnaryCall(
          getChannel(), getContainsPasswordMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.getUserResponse getUser(com.example.grpc.client.getUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUserMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.getUserTracksResponse getUserTracks(com.example.grpc.client.getUserTracksRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUserTracksMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.addTrackToUserResponse addTrackToUser(com.example.grpc.client.addTrackToUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddTrackToUserMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.getDefaultTracksResponse getDefaultTracks(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetDefaultTracksMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.getAllUserEmailsResponse getAllUserEmails(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetAllUserEmailsMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.client.deleteTrackResponse deleteTrack(com.example.grpc.client.deleteTrackRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteTrackMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreetingServiceFutureStub extends io.grpc.stub.AbstractStub<GreetingServiceFutureStub> {
    private GreetingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.addUserResponse> addUser(
        com.example.grpc.client.addUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddUserMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.containsUserResponse> containsUser(
        com.example.grpc.client.containsUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContainsUserMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.containsPasswordResponse> containsPassword(
        com.example.grpc.client.containsPasswordRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContainsPasswordMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.getUserResponse> getUser(
        com.example.grpc.client.getUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.getUserTracksResponse> getUserTracks(
        com.example.grpc.client.getUserTracksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserTracksMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.addTrackToUserResponse> addTrackToUser(
        com.example.grpc.client.addTrackToUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddTrackToUserMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.getDefaultTracksResponse> getDefaultTracks(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDefaultTracksMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.getAllUserEmailsResponse> getAllUserEmails(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllUserEmailsMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.client.deleteTrackResponse> deleteTrack(
        com.example.grpc.client.deleteTrackRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteTrackMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_USER = 0;
  private static final int METHODID_CONTAINS_USER = 1;
  private static final int METHODID_CONTAINS_PASSWORD = 2;
  private static final int METHODID_GET_USER = 3;
  private static final int METHODID_GET_USER_TRACKS = 4;
  private static final int METHODID_ADD_TRACK_TO_USER = 5;
  private static final int METHODID_GET_DEFAULT_TRACKS = 6;
  private static final int METHODID_GET_ALL_USER_EMAILS = 7;
  private static final int METHODID_DELETE_TRACK = 8;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreetingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreetingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_USER:
          serviceImpl.addUser((com.example.grpc.client.addUserRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.addUserResponse>) responseObserver);
          break;
        case METHODID_CONTAINS_USER:
          serviceImpl.containsUser((com.example.grpc.client.containsUserRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.containsUserResponse>) responseObserver);
          break;
        case METHODID_CONTAINS_PASSWORD:
          serviceImpl.containsPassword((com.example.grpc.client.containsPasswordRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.containsPasswordResponse>) responseObserver);
          break;
        case METHODID_GET_USER:
          serviceImpl.getUser((com.example.grpc.client.getUserRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.getUserResponse>) responseObserver);
          break;
        case METHODID_GET_USER_TRACKS:
          serviceImpl.getUserTracks((com.example.grpc.client.getUserTracksRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.getUserTracksResponse>) responseObserver);
          break;
        case METHODID_ADD_TRACK_TO_USER:
          serviceImpl.addTrackToUser((com.example.grpc.client.addTrackToUserRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.addTrackToUserResponse>) responseObserver);
          break;
        case METHODID_GET_DEFAULT_TRACKS:
          serviceImpl.getDefaultTracks((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.getDefaultTracksResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_USER_EMAILS:
          serviceImpl.getAllUserEmails((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.getAllUserEmailsResponse>) responseObserver);
          break;
        case METHODID_DELETE_TRACK:
          serviceImpl.deleteTrack((com.example.grpc.client.deleteTrackRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.client.deleteTrackResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreetingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreetingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.client.GreetingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GreetingService");
    }
  }

  private static final class GreetingServiceFileDescriptorSupplier
      extends GreetingServiceBaseDescriptorSupplier {
    GreetingServiceFileDescriptorSupplier() {}
  }

  private static final class GreetingServiceMethodDescriptorSupplier
      extends GreetingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreetingServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreetingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreetingServiceFileDescriptorSupplier())
              .addMethod(getAddUserMethodHelper())
              .addMethod(getContainsUserMethodHelper())
              .addMethod(getContainsPasswordMethodHelper())
              .addMethod(getGetUserMethodHelper())
              .addMethod(getGetUserTracksMethodHelper())
              .addMethod(getAddTrackToUserMethodHelper())
              .addMethod(getGetDefaultTracksMethodHelper())
              .addMethod(getGetAllUserEmailsMethodHelper())
              .addMethod(getDeleteTrackMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
