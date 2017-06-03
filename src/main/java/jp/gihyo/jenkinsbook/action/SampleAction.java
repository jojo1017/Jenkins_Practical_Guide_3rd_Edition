package jp.gihyo.jenkinsbook.action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.gihyo.jenkinsbook.dto.SampleDTO;

/**
 * DTO class for SampleServlet.
 */
public class SampleAction {
    /**
     * First name of the user.
     */
    private String firstName;
    /**
     * Last name of the user.
     */
    private String lastName;

    /**
     * Constructor of SampleAction.
     */
    public SampleAction() {
        this(null, null);
    }

    /**
     * Constructor of SampleAction.
     * @param fstName first name of the user
     * @param lstName last name of the user
     */
    public SampleAction(final String fstName, final String lstName) {
        this.firstName = fstName;
        this.lastName = lstName;
    }

    /**
     * Check parameter of http servlet request.
     * @param request HttpServletRequest
     * @return check result
     */
    public final boolean checkParameter(final HttpServletRequest request) {

        firstName = request.getParameter("FirstName");
        if ((firstName == null) || ("".equals(firstName))) {
            return false;
        }

        lastName = request.getParameter("LastName");
        if ((lastName == null) || ("".equals(lastName))) {
            return false;
        }

        return true;
    }

    /**
     * Execute action.
     * @param request HttpServletRequest
     * @return result jsp file path
     */
    public final String execute(final HttpServletRequest request) {
        SampleDTO dto = new SampleDTO(firstName, lastName);

        Calendar calender =Calendar.getInstance();
        int hour= calender.get(Calendar.HOUR_OF_DAY);
        if(hour<12){
        	dto.setMessage("goog mornin");
        }else{
        	dto.setMessage("good afternoon");
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("dto", dto);

        return "./WEB-INF/result.jsp";
    }

    /**
     * Decide the action.
     * @param name the action name
     * @return selected action by the action name
     */
    public final SampleAction createAction(final String name) {

        if ("hello".equals(name)) {
            return new SampleAction();
        } else {
            return null;
        }
    }



 }
