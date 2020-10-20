package be.pxl.ja.streamingservice.exception;

import be.pxl.ja.streamingservice.model.Profile;

import java.io.IOException;
import java.io.UncheckedIOException;

public class TooManyProfilesException extends Exception {

    public TooManyProfilesException(Profile profile, String message) {
        super(profile.toString() + message);
    }
}
