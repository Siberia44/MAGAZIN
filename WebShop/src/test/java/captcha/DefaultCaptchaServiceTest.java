package captcha;

import dao.ICaptchaDao;
import dao.impl.CaptchaDaoImpl;
import dto.CaptchaDTO;
import entity.Captcha;
import exception.SessionTimeOutException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.impl.CaptchaServiceImpl;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCaptchaServiceTest {

    private static final String CAPTCHA_NUMBERS = "666";
    private ICaptchaDao dao = new CaptchaDaoImpl();

    @Mock
    private HttpServletRequest request;

    @Mock
    private Captcha captcha;

    @Mock
    private CaptchaHandler captchaHandler;

    @Mock
    private CaptchaDTO captchaDTO;

    @InjectMocks
    private CaptchaServiceImpl captchaService;

    @Before
    public void setUp() {
        captchaService = new CaptchaServiceImpl(dao);
    }

    @Test
    public void createCaptcha() throws NoSuchAttributeException {
        captchaService.create();
    }

    @Test
    public void checkCaptchaOnValidAndReturnTrueWhenCaptchaIsValid() throws SessionTimeOutException {
        Mockito.when(captcha.getNumbers()).thenReturn(CAPTCHA_NUMBERS);
        Mockito.when(captchaDTO.getCaptchaNumbers()).thenReturn(CAPTCHA_NUMBERS);
        assertTrue(captchaService.checkCaptchaOnValid(captchaDTO, captcha));
    }

}

