public class RountingTable
{
	public VirtualCircle table[];
	public int max;
	
	public RountingTable()
	{
		table = new VirtualCircle[5];
		
		VirtualCircle Server1 = new VirtualCircle("14.163.200.227",2001,"Server1");
		VirtualCircle Server2 = new VirtualCircle("116.103.17.3",2002,"Server2");
		VirtualCircle Server3 = new VirtualCircle("116.105.173.174",2003,"Server3");
		
		table[0] = Server1;
		table[1] = Server2;
		table[2] = Server3;
		
		max=3;
		
	}	
}