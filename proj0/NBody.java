public class NBody {
  public static double readRadius(String fileName) {
    In in = new In(fileName);
    in.readDouble();
    return in.readDouble();
  }

  public static Planet[] readPlanets(String fileName) {
    In in = new In(fileName);
    int N = in.readInt();
    double r = in.readDouble();
    Planet[] planets = new Planet[N];
    for (int i = 0; i < N; i++) {
      planets[i] = new Planet();
      planets[i].xxPos = in.readDouble();
      planets[i].yyPos = in.readDouble();
      planets[i].xxVel = in.readDouble();
      planets[i].yyVel = in.readDouble();
      planets[i].mass = in.readDouble();
      planets[i].imgFileName = in.readString();
    }
    return planets;
}

public static void main(String[] args) {
  double T = Double.parseDouble(args[0]);
  double dt = Double.parseDouble(args[1]);
  String filename = args[2];
  double radius = NBody.readRadius(filename);
  Planet[] planets = NBody.readPlanets(filename);
  double time = 0;
  StdDraw.setScale(-radius,radius);
  StdDraw.clear();
  StdDraw.picture(0,0,"images/starfield.jpg");
  for (Planet oneP : planets) {
    oneP.draw();
  }
  StdDraw.show(2000);
  StdAudio.loop("audio/2001.mid");
    while (time <= T) {
        for (Planet oneP : planets) {
            double fx = oneP.calcNetForceExertedByX(planets);
            double fy = oneP.calcNetForceExertedByY(planets);
            oneP.update(dt,fx,fy);
        }
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for (Planet oneP : planets) {
            oneP.draw();
        }
        StdDraw.show(10);
        time += dt;
    }
}
}
