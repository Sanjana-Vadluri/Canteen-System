package com.canteen.canteensystem.model;

public enum OrderStatus {
    PENDING,        // just placed
    PREPARING,      // kitchen started
    READY,          // ready for pick‑up
    COMPLETED       // picked up / delivered
}
