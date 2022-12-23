FROM ghcr.io/graalvm/graalvm-ce:ol9-java17-22 AS builder

WORKDIR /build

RUN gu install native-image

COPY target/**-jar-with-dependencies.jar /build

RUN native-image -jar **-jar-with-dependencies.jar app --no-fallback -H:+ReportExceptionStackTraces

# The deployment Image
FROM ghcr.io/graalvm/jdk:ol9-java17-22

# Copy the native executable into the containers
COPY --from=builder /build/app /app

ENTRYPOINT ["/app/app"]