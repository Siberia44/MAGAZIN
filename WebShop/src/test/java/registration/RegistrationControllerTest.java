package registration;

import captcha.CaptchaHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.ICaptchaService;
import service.IUserService;
import servlet.ValidationDataInput;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    private static final String LOGIN = "login";
    private static final int ONE_TIME = 1;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CaptchaHandler captchaHandler;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ICaptchaService captchaService;

    @Mock
    private IUserService userService;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private ValidationDataInput controller;

    @Before
    public void setUp() {
        Mockito.when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
        Mockito.when(request.getParameter(Mockito.anyString())).thenReturn(LOGIN);
    }

    @Test
    public void sendUserToRegistrationPageWhenInputLoginIsExistInDB() throws ServletException, IOException {
        Mockito.when(userService.isUserPresent(LOGIN)).thenReturn(true);
        controller.doPost(request, response);
        Mockito.verify(dispatcher, Mockito.times(ONE_TIME)).forward(request, response);
    }


    @Test
    public void sendUserToMainShopPageWhenSuccessfulLogin() throws ServletException, IOException {
        Mockito.when(userService.isUserPresent(LOGIN)).thenReturn(false);
        Mockito.when(captchaService.checkCaptchaOnValid(request, captchaHandler)).thenReturn(true);
        controller.doPost(request, response);
        Mockito.verify(dispatcher, Mockito.times(ONE_TIME)).forward(request, response);
    }
}