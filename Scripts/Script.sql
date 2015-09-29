SELECT
    *
FROM
    kaoqin
WHERE
    ename = '朱伟亮'
    AND EXTRACT(
        YEAR_MONTH
    FROM
        '2015-8-9'
    ) = EXTRACT(
        YEAR_MONTH
    FROM
        clock
    )
ORDER BY
    clock ASC;

SELECT
    *
FROM
    kaoqin
WHERE
    ename = '朱伟亮'
    AND clock BETWEEN '2015/08/01' AND '2015/09/01'
ORDER BY
    clock ASC;

SELECT
    dep,
    ename,
    ckno
FROM
    kaoqin
WHERE
    clock BETWEEN '2015/08/01' AND '2015/09/01'
GROUP BY
    ename,
    ckno
ORDER BY
    ckno ASC;