package service;

public interface IUserService {
    /**
     * checks user by name
     *
     * @param name
     * @return true if user exist
     */
    boolean isUserPresent(String name);
}
