package app.explorerpost2.svc;

import app.explorerpost2.bean.PasswordResetBean;
import app.explorerpost2.form.MemberFormBean;

public interface MemberManagerSvc {

	public void addMember(MemberFormBean mfb, String pmCrUser) throws Exception;
	public void editMemberNotifyPreferences(MemberFormBean mfb, String pmAudtUser) throws Exception;
	public void editMemberRoles(MemberFormBean mfb, String pmAudtUser) throws Exception;
	public void editActiveStatus(MemberFormBean mf) throws Exception;
	public void resetMemberPassword(PasswordResetBean prb) throws Exception;

}
