package model;

public class Pixel implements Component {

	private int x;
	private int y;
	private boolean activo = true;
	private final boolean bala;

	public Pixel(int x, int y) {
		this(x, y, false);
	}

	public Pixel(int x, int y, boolean pBala) {
		this.x = x;
		this.y = y;
		this.bala = pBala;
	}

	@Override
	public void mover(int dx, int dy) {
		if (bala) {
			if (!activo) {
				return;
			}
			int oldX = x;
			int oldY = y;
			x += dx;
			y += dy;
			if (y < 0) {
				activo = false;
			}
			notificar(oldX, oldY, x, y);	//TODO revisar
		} else {
			x += dx;
			y += dy;
		}
	}

	// A PARTIR DE AQUI ES DEL COMPOSITE, YO NO SE - DANII
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public boolean isActivo() {
		return activo;
	}

	@Override
	public void setActivo(boolean b) {
		this.activo = b;
	}

	@Override
	public void notificarDisparoNuevo() {
		if (proyectil) {
			Espacio.getEspacio().notificarDisparoNuevo(getRefX(), getRefY());
		}
	}
}