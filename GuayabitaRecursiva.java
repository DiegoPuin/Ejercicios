package Ejercicios;

import java.util.Random;
import javax.swing.JOptionPane;

public class GuayabitaRecursiva {

	private Random aleatorio = new Random(System.nanoTime());
	
	public GuayabitaRecursiva() {
		iniciaJuego(1000000, 1000000, 1, 200, lanzarDado());
	}

	public void iniciaJuego(int monto1, int monto2, int turno, int pozo, int lanzamiento) {
		System.out.println("MONTO 1: " + monto1 + ", MONTO 2: " + monto2 + ", TURNO: " + turno + ", POZO: " + pozo);
		int apuesta = apostar(pozo); //Unica asignacion
		System.out.println("LANZAMIENTO: " + lanzamiento + ", APUESTA: " + apuesta + "\n");
		
		if (monto1 <= 0) {
			JOptionPane.showMessageDialog(null, "EL JUGADOR 1 HA PERDIDO", "FIN", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

		if (monto2 <= 0) {
			JOptionPane.showMessageDialog(null, "EL JUGADOR 2 HA PERDIDO", "FIN", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

		if (pozo <= 0) {
			if ("Y" == JOptionPane.showInputDialog("JUEGO TERMINADO, SEGUIR JUGANDO? (Y or N)")) {
				iniciaJuego(monto1-100, monto2-100, turno, 200, lanzarDado());
			} else {
				System.exit(0);
			}
		}

		if (turno == 1) {
			if (lanzamiento == 1) {
				iniciaJuego(monto1-100, monto2, 2, pozo+100, lanzarDado());
			}
			if (lanzamiento == 6) {
				iniciaJuego(monto1+100, monto2, 2, pozo-100, lanzarDado());
			}
			if (lanzamiento >= 2 && lanzamiento <= 5) {
				if (lanzamiento == 5) {
					if(JOptionPane.showInputDialog("Digite 'Y' si desea apostar y 'N' si no es asi").equalsIgnoreCase("Y")) {
						if (lanzarDado2() > lanzamiento) {
							iniciaJuego(monto1+apuesta, monto2, 2, pozo-apuesta, lanzarDado());
						} else {
							iniciaJuego(monto1-apuesta, monto2, 2, pozo+apuesta, lanzarDado());
						}
					}
				} else {
					if (lanzarDado2() > lanzamiento) {
						iniciaJuego(monto1+apuesta, monto2, 2, pozo-apuesta, lanzarDado());
					} else {
						iniciaJuego(monto1-apuesta, monto2, 2, pozo+apuesta, lanzarDado());
					}
				}
			}
		}
		
		if(turno == 2) {
			if (lanzamiento == 1) {
				iniciaJuego(monto1, monto2-100, 1, pozo+100, lanzarDado());
			}
			if (lanzamiento == 6) {
				iniciaJuego(monto1, monto2+100, 1, pozo-100, lanzarDado());
			}
			if (lanzamiento >= 2 && lanzamiento <= 5) {
				if (lanzamiento == 5) {
					if(JOptionPane.showInputDialog("Digite 'Y' si desea apostar y 'N' si no es asi").equalsIgnoreCase("Y")) {
						if (lanzarDado2() > lanzamiento) {
							iniciaJuego(monto1, monto2+apuesta, 1, pozo-apuesta, lanzarDado());
						} else {
							iniciaJuego(monto1, monto2-apuesta, 1, pozo+apuesta, lanzarDado());
						}
					} else {
						iniciaJuego(monto1, monto2, 1, pozo, lanzarDado());
					}
				} else {
					if (lanzarDado2() > lanzamiento) {
						iniciaJuego(monto1+apuesta, monto2, 1, pozo-apuesta, lanzarDado());
					} else {
						iniciaJuego(monto1-apuesta, monto2, 1, pozo+apuesta, lanzarDado());
					}
				}
			}
		}
	}

	public int lanzarDado() {
		return aleatorio.nextInt(6)+1;
	}

	public int apostar(int pozo) {
		return aleatorio.nextInt(1001)+pozo;
	}

	public int lanzarDado2() {
		return aleatorio.nextInt(6)+1;
	}
}