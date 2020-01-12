package p3.col;

public class FtpArtworkSender implements ArtworkSender {
    @Override
    public void sendArtwork(String artworkPath, Recipient recipient) {

    }

    @Override
    public String getFriendlyName() {
        return "File transfer protocol";
    }

    @Override
    public String getShortName() {
        return "ftp";
    }
}
