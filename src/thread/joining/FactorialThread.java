package thread.joining;

import java.math.BigInteger;

public class FactorialThread extends Thread {
    private final long _inputNumber;
    private BigInteger _result;
    private boolean _isFinished;

    public FactorialThread(long inputNumber) {
        this._inputNumber = inputNumber;
        this._result = BigInteger.ZERO;
        this._isFinished = false;
    }

    @Override
    public void run() {
        _result = factorial(_inputNumber);
        _isFinished = true;
    }

    private BigInteger factorial(long n) {
        BigInteger tmpResult = BigInteger.ONE;
        for (long i = n; i > 0; i--) {
            tmpResult = tmpResult.multiply(new BigInteger(Long.toString(i)));
        }
        return tmpResult;
    }

    public BigInteger getResult() {
        return _result;
    }

    public boolean isFinished() {
        return _isFinished;
    }
}
