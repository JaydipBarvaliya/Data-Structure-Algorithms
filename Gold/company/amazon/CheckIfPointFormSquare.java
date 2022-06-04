package com.company.amazon;

public class CheckIfPointFormSquare {

    public static void main(String[] args) {
        //Let's Check for  Square
        Point p1 = new Point(20, 10);
        Point p2 = new Point(10, 20);
        Point p3 = new Point(20, 20);
        Point p4 = new Point(10, 10);

        System.out.println(isSquare(p1, p2, p3, p4));

        // Let's Check for Rectangle
        p1 = new Point(10, 20);
        p2 = new Point(10, 10);
        p3 = new Point(50, 10);
        p4 = new Point(50, 20);
        System.out.println(isSquare(p1, p2, p3, p4));

        // Random AmazonPrimeOrdersSorting Case fail
        p1 = new Point(1, 1);
        p2 = new Point(-1, 1);
        p3 = new Point(-1, 1);
        p4 = new Point(-1, 1);
        System.out.println(isSquare(p1, p2, p3, p4));

        // Random AmazonPrimeOrdersSorting Case Pass
        p1 = new Point(1, 1);
        p2 = new Point(-1, 1);
        p3 = new Point(-1, -1);
        p4 = new Point(1, -1);
        System.out.println(isSquare(p1, p2, p3, p4));

        p1 = new Point(1, 0);
        p2 = new Point(0, -1);
        p3 = new Point(-1, 0);
        p4 = new Point(0, 1);
        System.out.println(isSquare(p1, p2, p3, p4));


    }

    public static boolean isSquare(Point p1, Point p2, Point p3, Point p4) {
        Boolean p1_p2 = fallOnSameSide(p1, p2);
        Boolean p1_p3 = fallOnSameSide(p1, p3);
        Boolean p1_p4 = fallOnSameSide(p1, p4);

        if (p1_p2) {
            return isSquareUtil(p1, p2, p3, p4);
        } else if (p1_p3) {
            return isSquareUtil(p1, p3, p2, p4);
        } else if (p1_p4) {
            return isSquareUtil(p1, p4, p2, p3);
        }
        return false;
    }

    private static boolean isSquareUtil(Point a, Point b, Point c, Point d) {
        if (fallOnSameSide(c, d)) {
            int x_diff = Math.abs(a.x - c.x);
            int y_diff_left = Math.abs(b.y - a.y);
            int y_diff_right = Math.abs(d.y - c.y);
            return x_diff == y_diff_left && x_diff == y_diff_right;
        }
        return false;
    }

    private static Boolean fallOnSameSide(Point p1, Point p2) {
        return p1.x == p2.x && p1.y != p2.y;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
