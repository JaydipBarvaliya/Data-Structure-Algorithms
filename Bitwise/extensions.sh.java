for f in *; do 
    mv -- "$f" "${f%}.java"
done