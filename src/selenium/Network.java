package selenium;

public abstract class Network {
    String userName;
    String password;

    Network() {}
    public boolean post(String message) throws InterruptedException {
        if (logIn(this.userName, this.password)) {
            boolean result =  sendData(message.getBytes());
            return result;
        }
        return true;
    }

    abstract boolean logIn(String userName, String password) throws InterruptedException;
    abstract boolean sendData(byte[] data) throws InterruptedException;
}
