package egovframework.servicename.utils.codeEnums;

public enum COMM_ERR_CODE {
	SUCCESS				("00","정상 처리되었습니다."),
	FORBIDDEN			("01","권한이 존재하지 않습니다."),
	EMPTY_LOGIN			("02","로그인정보가 없습니다."),
	EMPTY_MUST_PARAM	("03","필수 파라미터가 존재하지 않습니다."),
	EMPTY_DATA			("04","데이터가 존재하지 않습니다."),
	ERROR_SQL			("05","SQL 오류가 발생하였습니다."),
	CSRF				("05","사이트 위변조 방지를 위하여 새로고침이 필요합니다."),
	ERROR				("99","오류가 발생되었습니다."),
	ERR_DEFAULT			("-1","알수없는 에러 코드입니다.");
	public final String code;
	public final String message;
	private COMM_ERR_CODE(
			String errcode,
			String errmessage
	){
		code = errcode;
		message = errmessage;
	}
	public String getErrCode(){
		return code;
	}
	public String getErrMsg(){
		return message;
	}
	public static COMM_ERR_CODE getError(String code){
		if(code==null){
			return COMM_ERR_CODE.ERR_DEFAULT;
		}else{
			for(COMM_ERR_CODE e : COMM_ERR_CODE.values()){
				if(e.code.equals(code)){
					return e;
				}
			}
			return COMM_ERR_CODE.ERR_DEFAULT;
		}
	}
}
