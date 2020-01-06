# Absorbing markov chain

Calculating the probability of reaching each of the terminal states in an absorbing markov chain.

# E.g.

Given the input:
```
[
  [0,1,0,0,0,1],  # s0, the initial state, goes to s1 and s5 with equal probability
  [4,0,0,3,2,0],  # s1 can become s0, s3, or s4, but with different probabilities
  [0,0,0,0,0,0],  # s2 is terminal, and unreachable
  [0,0,0,0,0,0],  # s3 is terminal
  [0,0,0,0,0,0],  # s4 is terminal
  [0,0,0,0,0,0],  # s5 is terminal
]
```

```
s2 has probability 0
s3 has probability 3/14
s4 has probability 1/7
s5 has probability 9/14
```
