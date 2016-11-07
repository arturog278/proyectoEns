package mx.uaemex.fi.ens.fase_5;

public class Contador {

	public Contador() {
		// TODO Auto-generated constructor stub
	}
	
	public String suma(String cp,int sumando){
		int cpDec = Contador.hex2decimal(cp);
		String cpNew = Integer.toHexString(cpDec+sumando).toUpperCase();
		if(cpNew.length()==1){
			return "000"+cpNew;
		}else if(cpNew.length()==2){
			return "00"+cpNew;
		}else if(cpNew.length()==3){
			return "0"+cpNew;
		}
		return cpNew;
	}
	
	private static int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

}
