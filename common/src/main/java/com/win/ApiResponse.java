package com.win;

public record ApiResponse<T>(
        T data
) {}
