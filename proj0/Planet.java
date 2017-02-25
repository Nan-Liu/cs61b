public class Planet {
  double xxPos;
  double yyPos;
  double xxVel;
  double yyVel;
  double mass;
  String imgFileName;

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet() {}

  public Planet(Planet P) {
    xxPos = P.xxPos;
    yyPos = P.yyPos;
    xxVel = P.xxVel;
    yyVel = P.yyVel;
    mass = P.mass;
    imgFileName = P.imgFileName;
  }

  public double calcDistance(Planet target) {
    return Math.sqrt(Math.pow((target.xxPos - xxPos), 2) + Math.pow((target.yyPos - yyPos), 2));
  }

  public double calcForceExertedBy(Planet target) {
    double r = calcDistance(target);
    double F = 6.67*Math.pow(10,-11) * mass * target.mass / (r*r);
    return F;
  }

  public double calcForceExertedByX(Planet target) {
    double dx = target.xxPos - xxPos;
    double r = calcDistance(target);
    double F = calcForceExertedBy(target);
    return F * dx / r;
  }

  public double calcForceExertedByY(Planet target) {
    double dy = target.yyPos - yyPos;
    double r = calcDistance(target);
    double F = calcForceExertedBy(target);
    return F * dy / r;
  }

  public double calcNetForceExertedByX(Planet[] targets) {
    double sumX = 0.0;
    for (Planet target : targets) {
      if (! this.equals(target)) {
        sumX +=calcForceExertedByX(target);
      }
    }
    return sumX;
  }

  public double calcNetForceExertedByY(Planet[] targets) {
    double sumY = 0.0;
    for (Planet target : targets) {
      if (! this.equals(target)) {
        sumY +=calcForceExertedByY(target);
      }
    }
    return sumY;
  }

  public void update(double dt, double fX, double fY) {
    double ax = fX / mass;
    double ay = fY / mass;
    xxVel = xxVel + dt * ax;
    yyVel = yyVel + dt * ay;
    xxPos = xxPos + dt * xxVel;
    yyPos = yyPos + dt *yyVel;
  }

  public void draw() {
      StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
  }
}
