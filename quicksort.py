import random
import time


def generate(n, lo, hi):
    return [random.randint(lo, hi) for _ in range(n)]


def insertion(a):
    for i in range(1, len(a)):
        key = a[i]
        j = i - 1
        while j >= 0 and a[j] > key:
            a[j + 1] = a[j]
            j -= 1
        a[j + 1] = key
    return a


def quick(a):
    if len(a) <= 16:
        return insertion(a)

    first = a[0]
    middle = a[len(a) // 2]
    last = a[-1]
    pivot = sorted((first, middle, last))[1]

    less = [x for x in a if x < pivot]
    equal = [x for x in a if x == pivot]
    greater = [x for x in a if x > pivot]

    return quick(less) + equal + quick(greater)


def quick_sort(a, asc=True):
    a[:] = quick(a)
    if not asc:
        a.reverse()
    return a


def is_sorted(a, asc=True):
    for i in range(len(a) - 1):
        if asc and a[i] > a[i + 1]:
            return False
        if not asc and a[i] < a[i + 1]:
            return False
    return True


if __name__ == "__main__":
    demo = generate(10, -20, 50)
    print("Масив:     ", demo)
    print("Зростання: ", quick_sort(demo.copy(), True))
    print("Спадання:  ", quick_sort(demo.copy(), False))

    for n in (1_000, 10_000, 100_000):
        base = generate(n, -10**6, 10**6)
        print(f"\nn = {n:,}")

        for asc in (True, False):
            a = base.copy()

            start = time.perf_counter()
            quick_sort(a, asc)
            elapsed = time.perf_counter() - start

            direction = "За зростанням" if asc else "За спаданням"
            result = is_sorted(a, asc)
            print(f"  {direction}: {elapsed:.4f} c, впорядкований: {result}")