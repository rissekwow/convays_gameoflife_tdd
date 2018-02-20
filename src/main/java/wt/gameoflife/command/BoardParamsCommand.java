package wt.gameoflife.command;

public class BoardParamsCommand {

	private int xLength;
	private int yLength;
	private int aliveCount;
	
	public int getxLength() {
		return xLength;
	}
	public void setxLength(int xLength) {
		this.xLength = xLength;
	}
	public int getyLength() {
		return yLength;
	}
	public void setyLength(int yLength) {
		this.yLength = yLength;
	}
	public int getAliveCount() {
		return aliveCount;
	}
	public void setAliveCount(int aliveCount) {
		this.aliveCount = aliveCount;
	}
	@Override
	public String toString() {
		return "BoardParamsCommand [xLength=" + xLength + ", yLength=" + yLength + ", aliveCount=" + aliveCount + "]";
	}
	
	
	
}
