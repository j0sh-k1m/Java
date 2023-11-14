const GCD = (a, b) => {
    if (a == 0) {
        return b;
    } else if (b == 0) {
        return a; 
    }
    if (a > b) {
        var num = a % b;
        return GCD(b, num);
    } else {
        var num1 = b % a;
        return GCD(a, num1);
    }
}

console.log(GCD(6, 12));