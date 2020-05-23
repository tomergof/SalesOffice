import java. io. IOException;
public class NegativePriceException extends IOException {
	public NegativePriceException (String error){
		super(error);
	}
}
