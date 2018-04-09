int right_encode(int x) {
    if (x < 0 || x >= 2^2040) {
        throw new IllegalArgumentException("invalid x");
    }
    int n = 1;
    int num = x;
    while (2^(8n) < x) {
        n++;
    }

    int x_i[] = new int[n];
    for (int i = n; n > 0; n--) {
        x_i[i] = num % 256;
        num /= 256;
    }

    int o_i[] = new int[n];
    for (int i = 1; i <= n; i++) {
        o_i[i] = x[i] % 8;
        // wrong
    }
}


}
