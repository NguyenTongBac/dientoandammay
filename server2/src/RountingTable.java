public class RountingTable
{
	public VirtualCircle table[];
	public int max;
	
	public RountingTable()
	{
		table = new VirtualCircle[5];
		
		VirtualCircle Server1 = new VirtualCircle("34.150.42.64",2001,"Server1");
		VirtualCircle Server2 = new VirtualCircle("35.241.99.190",2002,"Server2");
		VirtualCircle Server3 = new VirtualCircle("34.92.51.146",2003,"Server3");
		
		table[0] = Server1;
		table[1] = Server2;
		table[2] = Server3;
		
		max=3;
		
	}	
}